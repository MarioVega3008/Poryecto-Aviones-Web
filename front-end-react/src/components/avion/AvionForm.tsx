import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IAvionModel from "../../models/Avion";
import AvionService from "../../services/AvionServices";

export const AvionForm = () => {
	
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialAvionModel : IAvionModel = {
        id: null,
        title: "",
        description: "",
        timeLimit: 60,
        minimumPassingScore: 14.01,
        numberOfQuestions: 10,
        instructions: ""
    };

    //Hooks para gestionar el modelo
    const [avion, setAvion] = useState<IAvionModel>(initialAvionModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setAvion({ ...avion, [name]: value });
    };

		const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
			const { name, value } = event.target;
			setAvion({ ...avion, [name]: value });
	};

    const saveAvion = () => {        
      if(avion.id !== null)
      {
        AvionService.update(avion)
        .then((response: any) => {
          navigate("/avions");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
			  AvionService.create(avion)
          .then((response: any) => {    
            navigate("/avions");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

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


		return ( //JSX
			<div className="submit-form">				
					<div>
						{ avion.id !== null ? (<h1>Actualizado aviones {avion.title}</h1>) : (<h1>Registro de nuevo aviones</h1>) }            
						<div className="form-group">
						<label htmlFor="title">Título</label>
            <input
              type="text"
							placeholder="Ingrese el título del avion"
              className="form-control"
              id="title"
              required
              value={avion.title}
              onChange={handleInputChange}
              name="title"
            />
						<label htmlFor="description">Descripción</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese la descripción del examen"
              id="description"
              required
              value={avion.description}
              onChange={handleInputChange}
              name="description"
            />
						<label htmlFor="timeLimit">Tiempo límite (Minutos)</label>
            <input						
              type="number"
              className="form-control"
              id="timeLimit"
							max="180"
							min="30"
              required
              value={avion.timeLimit}
              onChange={handleInputChange}
              name="timeLimit"
            />
						<label htmlFor="minimumPassingScore">Nota mínima de aprobación</label>
            <input						
              type="number"
              className="form-control"
              id="minimumPassingScore"
							max="20"							
							step="0.01"
              value={avion.minimumPassingScore!}
              onChange={handleInputChange}
              name="minimumPassingScore"
            />
						<label htmlFor="numberOfQuestions">Número de preguntas</label>
            <input						
              type="number"
              className="form-control"
              id="numberOfQuestions"							
							min="1"
              required
              value={avion.numberOfQuestions!}
              onChange={handleInputChange}
              name="numberOfQuestions"
            />
						<label htmlFor="instructions">Instrucciones</label>
            <textarea	
              className="form-control"
							placeholder="Ingrese las instrucciones para el examen"
              id="instructions"              
              value={avion.instructions!}							
              onChange={handleTextAreaChange}
              name="instructions"
            />
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/aviones"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveAvion} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );

}