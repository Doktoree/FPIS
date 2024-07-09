import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { saveProduct } from "../../services/productService";
import "./AddProduct.css";

function UpdateProduct() {
  const location = useLocation();
  const { product } = location.state || {};

  const [ime, setIme] = useState(product ? product.name : "");
  const [cena, setCena] = useState(product ? product.price : "");
  const [opis, setOpis] = useState(product ? product.description : "");
  const navigate = useNavigate();

  const onSubmitHandler = async (event) => {
    if (isNaN(cena)) {
      alert("Price must be number!");
      return;
    }

    event.preventDefault();

    const updatedProduct = {
      productId: product.productId,
      name: ime,
      price: cena,
      description: opis,
    };

    await saveProduct(updatedProduct, updatedProduct.productId);
    alert("Product is updated!");
    navigate("/ProductView");
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
          Update product
        </button>
      </form>
    </div>
  );
}

export default UpdateProduct;
