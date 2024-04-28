import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { format } from "date-fns";
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
import BuildRoundedIcon from "@mui/icons-material/BuildRounded";

const RepairDetails = () => {
  const [repairs, setRepairs] = useState([]);
  const { repairCode } = useParams();
  const [totalAmount, setTotalAmount] = useState(0);

  const navigate = useNavigate();

  const handleTotalAmount = (repairCode) => {
    repairService
      .getTotalAmount(repairCode)
      .then((response) => {
        console.log("Total Amount...", response.data);
        setTotalAmount(response.data);
    });
  };

  const init = () => {
    console.log("RepairCode", repairCode);
    repairService
      .getByCode(repairCode)
      .then((response) => {
        console.log("Listing repair details...", response.data);
        setRepairs(response.data);
      })
      .catch((error) => {
        console.log("An error ocurred while listing repair details.", error);
      });
    handleTotalAmount(repairCode);
  };

  useEffect(() => {
    init();
  }, []);

  return (
    <TableContainer component={Paper}>
      <h2>Repair Order #{repairCode}</h2>
      <br />
      <Link
        to="/repair/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<BuildRoundedIcon />}
        >
          Edit Repair
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              ID
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Plate
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Repair Type
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Price
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Mileage
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Code
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {repairs.map((repair) => (
            <TableRow
              key={repair.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{repair.id}</TableCell>
              <TableCell align="left">{repair.plate}</TableCell>
              <TableCell align="left">{repair.repairType}</TableCell>
              <TableCell align="left">+{repair.repairPrice}</TableCell>
              <TableCell align="right">{repair.mileage}</TableCell>
              <TableCell align="right">{repair.repairCode}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Disc Reg Client
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Disc Mon-Thu
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Disc bonus
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Surch Car Age
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Surch Mileage
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Surch Delay Pickup
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              IVA(19%)
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Subtotal
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {repairs.map((repair) => (
            <TableRow
              key={repair.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              
              <TableCell align="left">-{repair.discRegClient}</TableCell>
              <TableCell align="left">-{repair.discMonThu}</TableCell>
              <TableCell align="left">-{repair.discBonus}</TableCell>
              <TableCell align="left">+{repair.surchCarage}</TableCell>
              <TableCell align="left">+{repair.surchCarmileage}</TableCell>
              <TableCell align="left">+{repair.surchDelay}</TableCell>
              <TableCell align="right">+{repair.iva}</TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                ${repair.totalAmount}
              </TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>


      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Checkin Date
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Finish Date
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Checkout Date
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {repairs.map((repair) => (
            <TableRow
              key={repair.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              
              
              <TableCell align="right">
                {repair.checkinDate
                  ? format(new Date(repair.checkinDate), "yyyy/MM/dd - HH:mm")
                  : "-"}
              </TableCell>
              <TableCell align="right">
                {repair.finishDate
                  ? format(new Date(repair.finishDate), "yyyy/MM/dd - HH:mm")
                  : "-"}
              </TableCell>
              <TableCell align="right">
                {repair.checkoutDate
                  ? format(new Date(repair.checkoutDate), "yyyy/MM/dd - HH:mm")
                  : "-"}
              </TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <h2>Total: ${totalAmount}</h2>
      <Link to="/repair/list">Back to List</Link>
    </TableContainer>
    
    
  );
};

export default RepairDetails;
