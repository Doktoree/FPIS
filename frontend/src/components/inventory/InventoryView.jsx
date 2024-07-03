import React, { useState } from 'react'
import { getAllInventories, getInventoryById } from '../../services/inventoryService';

function InventoryView({inventories}) {

   const[id, setId] = useState('');
   const[filteredInventory, setFilteredInventory] = useState(inventories);
   const [selectedInventory, setSelectedInventory] = useState(null);
   const searchHandler = async () =>{

        try {

            let result;
            if(isNaN(id)){
                alert('Id must be number!');
                return;
            }
            if(id.trim() == ''){
                result = await getAllInventories();
            }

            else{
                result = await getInventoryById(id);
            }
            
            setFilteredInventory(result);
            
        } catch (error) {
            alert(error.message);
        }

        

   }

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
            <tr key={inventory.inventoryId} onClick={() => selectHandler(inventory)}>
              <td>{inventory.inventoryId}</td>
              <td>{inventory.name}</td>
              <td>{inventory.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="mt-3">
        <button
          className="btn btn-info"
          //onClick={updateHandler}
        >
          Change Inventory
        </button>
      </div>
    </div>
  );
}

export default InventoryView