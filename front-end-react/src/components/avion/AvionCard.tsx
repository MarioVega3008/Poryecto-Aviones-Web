import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link } from 'react-router-dom';
import { useParams } from "react-router-dom";
import IAvionModel from "../../models/Avion";
import AvionService from "../../services/AvionServices";

export const AvionCard = () => {
  const { id }= useParams();

  const [avion, setAvion] = useState<IAvionModel>();

  useEffect(() => {
    if (id)
      getAvion(id);
  }, [id]);


  const getAvion = (id: any) => {
    AvionService.retrieve(id)
      .then((response: any) => {
        setAvion(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        avion ? (
          <div>          
          <h2>{avion.title}</h2>
          <p>{avion.description}</p>
          <ul>
            <li> <strong>Tiempo límite (mins) :</strong>  {avion.timeLimit} minutos</li>
            <li>Nota mínima : {avion.minimumPassingScore} /20</li>
            <li>Número de preguntas : {avion.numberOfQuestions}</li>
            <li>Instrucciones : {avion.instructions}</li>
          </ul>
          <br />
							<div className="btn-group" role="group">								
                <Link to={"/avions"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
							</div>
          </div>

        ) : 
        ( 
          <h1>No hay un avion activo</h1>
        )
      }
      </div>
    );
}