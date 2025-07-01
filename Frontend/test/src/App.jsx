import { useEffect, useState } from "react";
import axios from "axios";

const API_URL = "http://localhost:8080/employees";

export default function App() {
  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({ name: "", dateOfJoining: "", isActive: false });
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    try {
      const res = await axios.get(API_URL);
      setEmployees(res.data);
    } catch (err) {
      console.error("Error fetching employees:", err);
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === "checkbox" ? checked : value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await axios.put(`${API_URL}/${editingId}`, form);
      } else {
        await axios.post(API_URL, form);
      }
      setForm({ name: "", dateOfJoining: "", isActive: false });
      setEditingId(null);
      fetchEmployees();
    } catch (err) {
      console.error("Error saving employee:", err);
    }
  };

  const handleEdit = (emp) => {
    setForm({
      name: emp.name,
      dateOfJoining: emp.dateOfJoining,
      isActive: emp.isActive,
    });
    setEditingId(emp.id);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`);
      fetchEmployees();
    } catch (err) {
      console.error("Error deleting employee:", err);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4">
      <div className="max-w-xl mx-auto bg-white rounded-lg shadow p-6">
        <h1 className="text-2xl font-bold mb-4 text-center">Employee Management</h1>

        <form onSubmit={handleSubmit} className="space-y-3 mb-6">
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={form.name}
            onChange={handleChange}
            className="w-full p-2 border border-gray-300 rounded"
            required
          />

          <input
            type="date"
            name="dateOfJoining"
            value={form.dateOfJoining}
            onChange={handleChange}
            className="w-full p-2 border border-gray-300 rounded"
            required
          />

          <label className="flex items-center space-x-2">
            <input
              type="checkbox"
              name="isActive"
              checked={form.isActive}
              onChange={handleChange}
              className="w-4 h-4"
            />
            <span>Active</span>
          </label>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition cursor-pointer
            active:bg-blue-950 active:scale-80 transform duration-100 ease-in-out"
          >
            {editingId ? "Update" : "Add"} Employee
          </button>
        </form>

        <ul className="space-y-2">
          {employees.map((emp) => (
            <li
              key={emp.id}
              className="flex justify-between items-center p-3 bg-gray-50 rounded border"
            >
              <div>
                <p className="font-medium">{emp.name}</p>
                <p className="text-sm text-gray-600">
                  Joined: {emp.dateOfJoining} | {emp.isActive ? "Active" : "Inactive"}
                </p>
              </div>
              <div className="flex gap-2">
                <button
                  onClick={() => handleEdit(emp)}
                  className="text-sm bg-yellow-400 px-3 py-1 rounded hover:bg-yellow-500
                  cursor-pointer active:bg-yellow-950 active:scale-80 transform duration-100 ease-in-out"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(emp.id)}
                  className="text-sm bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600
                  cursor-pointer active:bg-red-950 active:scale-80 transform duration-100 ease-in-out"
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}