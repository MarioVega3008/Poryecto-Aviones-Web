import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Home } from "./components/Home";
import { AvionList } from "./components/avion/AvionList";
import { AvionForm } from "./components/avion/AvionForm";
import { AvionCard } from "./components/avion/AvionCard";

const title = "PROGRAMACION WEB 6515";
const description = "Aplicación web Aviones";

const App: React.FC = () => {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">        
        <Link to={"/"}  className="navbar-brand">
          PROGRAMACION WEB
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/aviones"} className="nav-link">
              AVIONES
            </Link>
          </li>          
        </div>
      </nav>
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/aviones" element={<AvionList />} />          
          <Route path="/aviones/create" element={<AvionForm />} />    
          <Route path="/aviones/retrieve/:id" element={<AvionCard/>} />      
          <Route path="/aviones/update/:id" element={<AvionForm />} />    
        </Routes>
      </div>
    </div>
  );
}
export default App;
