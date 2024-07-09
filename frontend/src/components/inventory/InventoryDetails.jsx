import React, { useEffect, useState } from "react";
import { Table, Button } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import {
  deleteInventoryItem,
  getInventoryById,
} from "../../services/inventoryService";

function InventoryDetails() {
  const location = useLocation();
  const { inventory } = location.state || {};
  const [selectedItem, setSelectedItem] = useState(null);
  const navigate = useNavigate();
  const [filteredInventory, setFilteredInventory] = useState(inventory);

  const selectHandler = (inventoryItem) => {
    setSelectedItem(inventoryItem);
  };

  const handleDeleteItem = async (event) => {
    try {
      event.preventDefault();
      if (selectedItem == null) {
        alert("There is no selected inventory!");
        return;
      }
      const inventoryId = filteredInventory.inventoryId;
      const inventoryItemId = selectedItem.inventoryItemId;
      await deleteInventoryItem(inventoryId, inventoryItemId);
      const updatedItems = filteredInventory.inventoryItems.filter(
        (item) => item.inventoryItemId !== inventoryItemId
      );

      setFilteredInventory({
        ...filteredInventory,
        inventoryItems: updatedItems,
      });
      alert("Inventory item is successfully deleted!");
    } catch (error) {
      alert(error.message);
    }
  };

  const handleAddItems = (event) => {
    event.preventDefault();

    navigate("/AddInventoryItem", {
      state: { inventory: inventory },
    });
  };

  return (
    <div className="container mt-4">
      <div className="row mb-3">
        <div className="col">
          <label htmlFor="inventoryId" className="form-label">
            Inventory ID:
          </label>
          <input
            type="text"
            className="form-control"
            id="inventoryId"
            value={inventory.inventoryId}
            readOnly
          />
        </div>
        <div className="col">
          <label htmlFor="name" className="form-label">
            Name:
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            value={inventory.name}
            readOnly
          />
        </div>
      </div>
      <div className="row mb-3">
        <div className="col">
          <label htmlFor="date" className="form-label">
            Date:
          </label>
          <input
            type="text"
            className="form-control"
            id="date"
            value={inventory.date}
            readOnly
          />
        </div>
      </div>
      <h3>Employees</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>ID</th>
            <th>Department</th>
          </tr>
        </thead>
        <tbody>
          {filteredInventory.employeeDtos.map((employee, index) => (
            <tr key={index}>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.employeeId}</td>
              <td>{employee.department.name}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <h3>Inventory Items</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Inventory item ID</th>
            <th>State</th>
            <th>Quantity</th>
            <th>Unit</th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Product ID</th>
          </tr>
        </thead>
        <tbody>
          {filteredInventory.inventoryItems.map((item) => (
            <tr key={item.inventoryItemId} onClick={() => selectHandler(item)}>
              <td>{item.inventoryItemId}</td>
              <td>{item.state}</td>
              <td>{item.quantity}</td>
              <td>{item.unit}</td>
              <td>{item.productDto.name}</td>
              <td>{item.productDto.price}</td>
              <td>{item.productDto.productId}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <div className="mt-3">
        <Button className="mr-2" variant="info" onClick={handleAddItems}>
          Add Inventory Items
        </Button>
        <Button variant="danger" onClick={handleDeleteItem}>
          Delete Inventory Item
        </Button>
      </div>
    </div>
  );
}

export default InventoryDetails;
