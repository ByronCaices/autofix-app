import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import CarList from "./components/CarsList";
import EditCar from "./components/EditCar";
import RepairList from "./components/RepairsList";
import RepairDetails from "./components/RepairDetails";
import EditRepair from "./components/EditRepair";
import AddRepair from "./components/AddRepair";
import BonusList from "./components/BonusList";
import AddEditBonus from "./components/AddEditBonus";
import AddCar from "./components/AddCar";

function App() {
  return (
    <Router>
      <div className="container">
        <Navbar></Navbar>
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="*" element={<NotFound />} />
          <Route path="/car/list" element={<CarList />} />
          <Route path="/car/edit/:plate" element={<EditCar />} />
          <Route path="/car/add" element={<AddCar />} />
          <Route path="/repair/list" element={<RepairList />} />
          <Route path="/repair/list/:repairCode" element={<RepairDetails />} />
          <Route path="/repair/edit/:id" element={<EditRepair />} />
          <Route path="/repair/add" element={<AddRepair />} />
          <Route path="/bonus/list" element={<BonusList />} />
          <Route path="/bonus/edit/:id" element={<AddEditBonus />} />
          <Route path="/bonus/add" element={<AddEditBonus />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
