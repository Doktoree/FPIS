import React, { useState } from "react";
import { createProduct, getProductById } from "../../services/productService";
import { saveInventory, createInventory } from "../../services/inventoryService";
import "bootstrap/dist/css/bootstrap.min.css";
import "./AddInventory.css";

function AddInventory({loggedInUser}) {
  const [ime, setIme] = useState("");
  const [stavkeInventara, setStavkeInventara] = useState([]);
  const [kolicina, setKolicina] = useState(1);
  const [jedinicaMere, setJedinicaMere] = useState("PCS");
  const [stanje, setStanje] = useState("NEW");
  const [sifraProizvoda, setSifraProizvoda] = useState("");
  const [inputState, setInputState] = useState(false);
  const units = ["PACK"];
  const states = ["USED", "DAMAGED", "RETURNED", "SOLD"];
  const user = loggedInUser;

  const handleAddItem = async (event) => {
    try {
      event.preventDefault();

      if(ime === ""){
        alert("Inventory name should not be empty!");
        return;
      }

      if(sifraProizvoda === ""){
        alert("Product ID should not be empty!");
        return;
      }

      let checkedProduct = await getProductById(sifraProizvoda);
      console.log(checkedProduct);

      const productDto = checkedProduct;

      const inventoryItem = {
        quantity: kolicina,
        unit: jedinicaMere,
        state: stanje,
        productDto: productDto
      };

      console.log(inventoryItem);

      setInputState(true);
      setStavkeInventara([...stavkeInventara, inventoryItem]);
    } catch (error) {
      alert(error.message);
    }
  };

  const saveInventoryHandler = async(event) =>{
    event.preventDefault();

    if(ime === ""){
      alert("Inventory name should not be empty!");
      return;
    }

    if(stavkeInventara.length === 0){
      alert("There must be at least 1 inventory item!");
      return;
    }

    const inventory = {
      name: ime,
      employeeDtos:[{employeeId: user.employee.employeeId}],
      inventoryItems:stavkeInventara
    }

    await createInventory(inventory);

    setStavkeInventara([]);
  }

  const handleDeleteItem = (index) => {
    setStavkeInventara(stavkeInventara.filter((_, i) => i !== index));
  }

  return (
    <div className="container mt-4">
      <div className="form-group">
        <label htmlFor="inventoryName">Inventory name:</label>
        <input
          type="text"
          className="form-control"
          id="inventoryName"
          value={ime}
          onChange={(e) => setIme(e.target.value)}
          disabled={inputState}
          required
        />
      </div>
      <hr />
      <form>
        <div className="form-group">
          <label htmlFor="productId">Product ID:</label>
          <input
            className="form-control"
            id="productId"
            value={sifraProizvoda}
            onChange={(e) => setSifraProizvoda(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="unit">Unit:</label>
          <select
            className="form-control"
            id="unit"
            value={jedinicaMere}
            onChange={(e) => setJedinicaMere(e.target.value)}
            required
          >
            <option value="PCS">PCS</option>
            {units.map((unitOption, index) => (
              <option key={index} value={unitOption}>
                {unitOption}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="quantity">Quantity:</label>
          <input
            type="number"
            className="form-control"
            id="quantity"
            value={kolicina}
            onChange={(e) => setKolicina(e.target.value)}
            min="1"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="state">State:</label>
          <select
            className="form-control"
            id="state"
            value={stanje}
            onChange={(e) => setStanje(e.target.value)}
            required
          >
            <option value="NEW">NEW</option>
            {states.map((stateOption, index) => (
              <option key={index} value={stateOption}>
                {stateOption}
              </option>
            ))}
          </select>
        </div>
        <button
          type="button"
          className="btn btn-secondary"
          onClick={handleAddItem}
        >
          Save inventory item
        </button>

        <div className="mt-4">
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Product ID</th>
                <th>Product name</th>
                <th>Unit</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>State</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {stavkeInventara.map((stavka, index) => (
                <tr key={index}>
                  <td>{stavka.productDto.productId}</td>
                  <td>{stavka.productDto.name}</td>
                  <td>{stavka.unit}</td>
                  <td>{stavka.productDto.price}</td>
                  <td>{stavka.quantity}</td>
                  <td>{stavka.state}</td>
                  <td>
                    <button type="button" className="btn btn-danger" onClick={() => handleDeleteItem(index)}>
                      Delete item
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <button type="submit" className="btn btn-primary" onClick={saveInventoryHandler}>
          Save inventory
        </button>
      </form>
    </div>
  );
}

export default AddInventory;

