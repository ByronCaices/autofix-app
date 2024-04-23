import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import carService from "../services/car.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";

const AddEditCar = () => {
  const { plateState } = useParams();
  var [plate, setPlate] = useState("");
  const [bodywork, setBodywork] = useState("");
  const [engine, setEngine] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [mileage, setMileage] = useState("");
  const [year, setYear] = useState("");
  const [seats, setSeats] = useState("");
  const [titleCarForm, setTitleCarForm] = useState("");
  const navigate = useNavigate();

  const handlePlateChange = (event) => {
    const newValue = event.target.value;
    setPlate(newValue);       // Actualiza la primera variable de estado
     // Actualiza la segunda variable de estado
  };

  const saveCar = (e) => {
    e.preventDefault();
    console.log("Save Car Plate: ", plateState);
    
    const car = { plate, bodywork, engine, brand, model, mileage, year, seats };
    console.log(car)
    car.plate = plateState;
    if (plateState) {
      //Actualizar Datos 
      carService
        .update(car)
        .then((response) => {
          console.log("Car ha sido actualizado.", response.data);
          navigate("/car/list");
        })
        .catch((error) => {
          //console.log(car)
          console.log(
            "An error ocurred while trying to update car.",
            error
          );
        });
    } else {
      //Crear nuevo
      
      carService
        .create(car)
        .then((response) => {
          console.log("Car has been added.", response.data);
          navigate("/car/list");
        })
        .catch((error) => {
          console.log(car)
          console.log(
            "An error ocurred while trying tu add new car.",
            error
          );
        });
    }
  };

  useEffect(() => {
    
    console.log("Edit Car Plate: ", plateState);
    if (plateState) {
      setTitleCarForm("Edit Car");
      
      //plate=plateState;
      carService
        .getByPlate(plateState)
        .then((car) => {
          setBodywork(car.data.bodywork);
          setBrand(car.data.brand);
          setEngine(car.data.engine);
          setModel(car.data.model);
          setMileage(car.data.mileage);
          setYear(car.data.year);
          setSeats(car.data.seats);
        })
        .catch((error) => {
          console.log("An error ocurred while trying to set car.", error);
        });
    } else {
      setTitleCarForm("Register New Car");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleCarForm} </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="plate"
            label="Plate"
            value={plateState}
            variant="standard"
            onChange={(e) => setPlate(e.target.value)}
            helperText="Ej. ABCD12"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="brand"
            label="Brand"
            value={brand}
            variant="standard"
            onChange={(e) => setBrand(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="model"
            label="Model"
            value={model}
            variant="standard"
            onChange={(e) => setModel(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="bodywork"
            label="Bodywork"
            value={bodywork}
            variant="standard"
            onChange={(e) => setBodywork(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="engine"
            label="Engine"
            value={engine}
            select
            variant="standard"
            defaultValue="diesel"
            onChange={(e) => setEngine(e.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={"diesel"}>diesel</MenuItem>
            <MenuItem value={"gas"}>gas</MenuItem>
            <MenuItem value={"electric"}>electric</MenuItem>
            <MenuItem value={"hybrid"}>hybrid</MenuItem>
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="mileage"
            label="Mileage"
            type="number"
            value={mileage}
            variant="standard"
            onChange={(e) => setMileage(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="year"
            label="Year"
            type="number"
            value={year}
            variant="standard"
            onChange={(e) => setYear(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="seats"
            label="Seats"
            type="number"
            value={seats}
            variant="standard"
            onChange={(e) => setSeats(e.target.value)}
          />
        </FormControl>

        

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveCar(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Save
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/car/list">Back to List</Link>
    </Box>
  );
};

export default AddEditCar;
