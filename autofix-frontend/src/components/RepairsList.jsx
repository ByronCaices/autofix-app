import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import CallMadeRoundedIcon from '@mui/icons-material/CallMadeRounded';
import BuildRoundedIcon from '@mui/icons-material/BuildRounded';

const RepairList = () => {
  const [repairs, setRepairs] = useState([]);



  const navigate = useNavigate();

  const init = () => {
    repairService
      .getAll()
      .then((response) => {
        console.log("Listing repair details...", response.data);
        setRepairs(response.data);
      })
      .catch((error) => {
        console.log(
          "An error ocurred while listing cars.",
          error
        );
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (id) => {
    console.log("Printing id...", id);
    const confirmDelete = window.confirm(
      "Are you sure to delete this repair?"
    );
    if (confirmDelete) {
      repairService
        .remove(id)
        .then((response) => {
          console.log("Repair has been deleted.", response.data);
          init(); // Reload the list of cars
        })
        .catch((error) => {
          console.log(
            "An error ocurred while trying to delete the repair.",
            error
          );
        });
    }
  };

  const handleRepairDetails = (repairCode) => {
    console.log("HandleRepairDetails", repairCode);
    navigate(`/repair/list/${repairCode}`);
  };

  const handleEdit = (id) => {
    console.log("HandleRepairDetails", id);
    navigate(`/repair/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/repair/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<PersonAddIcon />}
        >
          Add Repair
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Order Code
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Plate
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Repair Type
            </TableCell>
            
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Engine
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Bodywork
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Brand
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Mileage
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Total
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {repairs.map((repair) => (
            <TableRow
              key={repair.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              
              <TableCell align="left">{repair.repairCode}</TableCell>
              <TableCell align="left">{repair.plate}</TableCell>
              <TableCell align="left">{repair.repairType}</TableCell>
              
              <TableCell align="right">{repair.engine}</TableCell>
              <TableCell align="right">{repair.bodywork}</TableCell>
              <TableCell align="right">{repair.brand}</TableCell>
              <TableCell align="right">{repair.mileage}</TableCell>
              <TableCell align="right"  sx={{ fontWeight: "bold" }}>${repair.totalAmount}</TableCell>
              <TableCell>
                <Button
                  variant="outlined"
                  color="info"
                  size="small"
                  onClick={() => handleRepairDetails(repair.repairCode)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<CallMadeRoundedIcon />}
                >
                  Details
                </Button>

                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(repair.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<BuildRoundedIcon />}
                >
                  Edit
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(repair.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >DEL</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default RepairList;
