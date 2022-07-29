import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IAvionModel from '../../models/Avion';
import AvionService from '../../services/AvionServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const AvionList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [avions, setAvions] = useState<Array<IAvionModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listAvions(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listAvions(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listAvions = (page: number, size: number) => {
       AvionService.list(page, size)
         .then((response: any) => {
           setAvions(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      AvionService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeAvion = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar el avionen?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                AvionService.remove(id)
                .then((response: any) => {
                  listAvions(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} Aviones</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>AVION</th>
                            <th>PASAJERO</th>
                            <th>ASIENTO</th>
                            <th># VUELO</th>
                            <th>
                              <Link to={"/avions/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {avions && avions.map((Avion, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Avion.title}</td>
                                <td>{Avion.description}</td>
                                <td>{Avion.timeLimit} mins</td>
                                <td>{Avion.numberOfQuestions}</td>
                                <td>
                        
                                <div className="btn-group" role="group">
                                <Link to={"/avions/retrieve/" + Avion.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/avions/update/" + Avion.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeAvion(Avion.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior"/>

            </div>            
        </div>
     );
}