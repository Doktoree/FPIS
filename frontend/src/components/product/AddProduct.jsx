import React, { useState } from "react";
import { createProduct } from "../../services/productService";
import "./AddProduct.css";


function AddProduct() {
  const [ime, setIme] = useState("");
  const [cena, setCena] = useState("");
  const [opis, setOpis] = useState("");

  const onSubmitHandler = async (event) => {
    event.preventDefault();

    if(isNaN(cena)){
        alert('Price must be number!');
        return;
    }

    const product = {
      name: ime,
      price: cena,
      description: opis,
    };

    console.log(product);

    await createProduct(product);
    setIme('');
    setCena('');
    setOpis('');
  };

  return (
    <div className="container mt-4">
      <form onSubmit={onSubmitHandler}>
        <div className="form-group">
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            value={ime}
            onChange={(e) => setIme(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="price">Price:</label>
          <input
            type="text"
            className="form-control"
            id="price"
            name="price"
            value={cena}
            onChange={(e) => setCena(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description:</label>
          <input
            type="text"
            className="form-control"
            id="description"
            name="description"
            value={opis}
            onChange={(e) => setOpis(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add product
        </button>
      </form>
    </div>
  );
}

export default AddProduct;
