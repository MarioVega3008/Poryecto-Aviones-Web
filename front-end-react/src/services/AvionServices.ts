import Swal from "sweetalert2";
import http from "../http-common";
import IAvionData from "../models/Avion";

const create = async (data: IAvionData) => {    
  try {
    const response = await http.post<IAvionData>("/aviones", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El avion ha sido creado correctamente',
        confirmButtonText: 'Aceptar'    

      });
    }
    console.log(response);
  } catch (err) {
    console.log(err);
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
};

const retrieve = async (id: number) => {
    return http.get<IAvionData>(`/aviones/${id}`);
};

const update = async (data: IAvionData) => {
  try {    
    const response = await http.put<IAvionData>(`/aviones/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El avion ha sido actualizado',
        confirmButtonText: 'Aceptar'    
      });
    }

  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
    
};

const remove = async (id: number) => {
    try {
      const response = await  http.delete<string>(`/aviones/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'El avion ha sido eliminado',
          confirmButtonText: 'Aceptar'    
        });
      }
    } catch (error) {
      Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    }

};


const list = (page: number, size: number, sort? : String) => {
  const urlRequest : string = "/aviones/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<IAvionData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/aviones/count");
  return response.data;
};

const AvionService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count

};
export default AvionService;