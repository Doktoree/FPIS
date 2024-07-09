import React, { useState, useEffect } from "react";
import {
  getAllInventories,
  getInventoryById,
} from "../../services/inventoryService";
import { useNavigate } from "react-router-dom";

function InventoryView() {
  const [id, setId] = useState("");
  const [inventories, setInvetories] = useState([]);
  const [filteredInventory, setFilteredInventory] = useState(inventories);
  const [selectedInventory, setSelectedInventory] = useState(null);
  const [trigger, setTrigger] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchData() {
      try {
        const inventories = await getAllInventories();
        setInvetories(inventories);
        setFilteredInventory(inventories);
      } catch (error) {
        console.log("Greska!");
      }
    }

    fetchData();
  }, [trigger]);

  const searchHandler = async (event) => {
    event.preventDefault();
    try {
      let result;
      if (isNaN(id)) {
        alert("Id must be number!");
        return;
      }
      if (id.trim() === "") {
        result = await getAllInventories();
      } else {
        result = await getInventoryById(id);
        result = [result];
      }
      setFilteredInventory(result);
    } catch (error) {
      alert(error.message);
    }
  };

  const selectHandler = (inventory) => {
    setSelectedInventory(inventory);
  };

  const updateHandler = async (event) => {
    event.preventDefault();

    if (selectedInventory == null) {
      alert("There is no selected inventory!");
      return;
    }

    const response = await getInventoryById(selectedInventory.inventoryId);
    setTrigger((prev) => prev + 1);
    navigate("/InventoryDetails", {
      state: { inventory: response },
    });
  };

  return (
    <div className="container mt-4">
      <div className="form-inline mb-3">
        <input
          type="text"
          className="form-control mr-2"
          placeholder="Search by ID"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
        <button className="btn btn-primary" onClick={searchHandler}>
          Search
        </button>
      </div>

      <table className="table table-striped table-hover">
        <thead className="thead-dark">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Date</th>
          </tr>
        </thead>
        <tbody>
          {filteredInventory.map((inventory) => (
            <tr
              key={inventory.inventoryId}
              onClick={() => selectHandler(inventory)}
            >
              <td>{inventory.inventoryId}</td>
              <td>{inventory.name}</td>
              <td>{inventory.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="mt-3">
        <button className="btn btn-info" onClick={updateHandler}>
          Details
        </button>
      </div>
    </div>
  );
}

export default InventoryView;
