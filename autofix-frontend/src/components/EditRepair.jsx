import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
//import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider/LocalizationProvider";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';

const EditRepair = () => {
  const { id } = useParams();
  const [repairPrice, setRepairPrice] = useState("");
  const [repairType, setRepairType] = useState("");
  const [repairCode, setRepairCode] = useState("");
  const [plate, setPlate] = useState("");
  const [bodywork, setBodywork] = useState("");
  const [engine, setEngine] = useState("");
  const [brand, setBrand] = useState("");
  const [mileage, setMileage] = useState("");
  const [discRegClient, setDiscRegClient] = useState("");
  const [discMonThu, setDiscMonThu] = useState("");
  const [discBonus, setDiscBonus] = useState("");
  const [surchCarage, setSurchCarage] = useState("");
  const [surchCarmileage, setSurchCarmileage] = useState("");
  const [surchDelay, setSurchDelay] = useState("");
  const [checkinDate, setCheckinDate] = useState("");
  const [finishDate, setFinishDate] = useState("");
  const [checkoutDate, setCheckoutDate] = useState("");
  const [iva, setIva] = useState("");
  const [totalAmount, setTotalAmount] = useState("");
  const [titleRepairForm, setTitleRepairForm] = useState("");
  const navigate = useNavigate();


  const saveRepair = (e) => {

    e.preventDefault();
    console.log("1.Save Repair id: ", id);
    const repair = { id, repairPrice, repairType, repairCode, plate, bodywork, engine, brand, model, mileage, discRegClient, discMonThu, discBonus, surchCarage, surchCarmileage, surchDelay, checkinDate, finishDate: finishDate, checkoutDate, iva, totalAmount };
    if (id) {
      //Actualizar Datos 
      repairService
        .update(repair)
        .then((response) => {
          console.log("Repair ha sido actualizado.", response.data);
          navigate("/repair/list");
        })
        .catch((error) => {
          //console.log(car)
          console.log(
            "An error ocurred while trying to update repair.",
            error
          );
        });
    } else {
      //Crear nuevo
      repairService
        .create(repair)
        .then((response) => {
          console.log("Repair has been added.", response.data);
          navigate("/repair/list");
        })
        .catch((error) => {
          console.log(repair)
          console.log(
            "An error ocurred while trying tu add new repair.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleRepairForm("Edit Repair");
      console.log("YYYYY")
      repairService
        .getById(id)
        .then((repair) => {
            setRepairPrice(repair.data.repairPrice);
            setRepairType(repair.data.repairType);
            setRepairCode(repair.data.repairCode);
            setPlate(repair.data.plate);
            setBodywork(repair.data.bodywork);
            setEngine(repair.data.engine);
            setBrand(repair.data.brand);
            setModel(repair.data.model);
            setMileage(repair.data.mileage);
            setDiscRegClient(repair.data.discRegClient);
            setDiscMonThu(repair.data.discMonThu);
            setDiscBonus(repair.data.discBonus);
            setSurchCarage(repair.data.surchCarage);
            setSurchCarmileage(repair.data.surchCarmileage);
            setSurchDelay(repair.data.surchDelay);
            setCheckinDate(repair.data.checkinDate);
            setFinishDate(repair.data.finishDate);
            setCheckoutDate(repair.data.checkoutDate);
            setIva(repair.data.iva);
            setTotalAmount(repair.data.totalAmount);
        })
        .catch((error) => {
          console.log("An error ocurred while trying to set repair.", error);
        });
    } else {
      setTitleRepairForm("Register New Repair");
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
      <h3> {titleRepairForm} </h3>
      <hr />

            <form>
                <FormControl fullWidth>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DateTimePicker
                        label="Add repair finish date"
                        value={finishDate}
                        onChange={(e) => setFinishDate(e.target.value)}
                        renderInput={(params) => <TextField {...params} />}
                    />
                    </LocalizationProvider>
                </FormControl>

                <FormControl fullWidth>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DateTimePicker
                            label="Add checkout date"
                            value={checkoutDate}
                            onChange={(e) => setCheckoutDate(e.target.value)}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </FormControl>
                <FormControl>
                    <br />
                    <Button
                        variant="contained"
                        color="info"
                        onClick={saveRepair}
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

export default EditRepair;
