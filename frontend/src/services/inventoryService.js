const apiUrl = "http://localhost:8080/api/inventory";

export async function getAllInventories() {
  const response = await fetch(apiUrl);
  const data = await response.json();

  return data;
}

export async function getInventoryById(id) {
  const response = await fetch(apiUrl + "/" + id);
  if (!response.ok) {
    throw new Error("There is no inventory with the given ID!");
  }
  const data = await response.json();
  console.log(JSON.stringify(data));
  return data;
}

export async function createInventory(inventory) {
  const response = await fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(inventory),
  });

  if (!response.ok) {
    new Error("There is error in creating Inventory!");
  }

  const data = response.json();

  return data;
}

export async function saveInventory(id, inventory) {
  const response = await fetch(apiUrl + "/" + id, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(inventory),
  });

  if (!response.ok) {
    throw new Error("There is no product with given ID!");
  }
  const data = await response.json();

  return data;
}

export async function deleteInventoryItem(inventoryId, inventoryItemId) {
  const response = await fetch(
    apiUrl + "/" + inventoryId + "/" + inventoryItemId,
    {
      method: "DELETE",
    }
  );

  if (!response.ok) {
    const data = await response.text();
    throw new Error(data);
  }

  const data = await response.text();
  return data;
}

export async function updateInventory(inventory) {
  const response = await fetch(apiUrl, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(inventory),
  });

  if (!response.ok) {
    throw new Error("Error!");
  }
  const data = await response.text();

  return data;
}
