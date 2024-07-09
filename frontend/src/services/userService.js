const apiUrl = "http://localhost:8080/api/user";

export async function checkUser(user) {
  const response = await fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  if (!response.ok) {
    throw new Error("There is no user with that username and password!");
  }

  const data = await response.json();

  return data;
}
