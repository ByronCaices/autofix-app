import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home';
import NotFound from './components/NotFound';
import CarList from './components/CarsList';
import AddEditCar from './components/AddEditCar';

function App() {
  return (
      <Router>
          <div className="container">
          <Navbar></Navbar>
            <Routes>
              <Route path="/home" element={<Home/>} />
              <Route path="*" element={<NotFound/>} />
              <Route path="/car/list" element={<CarList/>} />
              <Route path="/car/edit/:plateState" element={<AddEditCar/>} />
              <Route path="/car/add" element={<AddEditCar/>} />
            </Routes>
          </div>
      </Router>
  );
}

export default App
