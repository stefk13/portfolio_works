import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios'
import '../../CSS/form.css'

const UpdateEmployee = () => {
  const { id } = useParams();
  const [employeeData, setEmployeeData] = useState(null);
  const [formData, setFormData] = useState({
    first_name: '',
    last_name: '',
    email: '',
    gender: '',
    salary: '',
  })

  useEffect(() => {
    const fetchEmployeeData = async () => {
      try {
        const response = await axios.get(`http://localhost:3001/api/v1/emp/employees/${id}`)
        setEmployeeData(response.data)
        setFormData({
          first_name: response.data.first_name,
          last_name: response.data.last_name,
          email: response.data.email,
          gender: response.data.gender,
          salary: response.data.salary,
        });
      } catch (error) {
        console.error('Error fetching employee data:', error)
      }
    }

    fetchEmployeeData()
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:3001/api/v1/emp/employees/${id}`, formData)
      window.location.href = '/employee-list'
    } catch (error) {
      console.error('Error updating employee data:', error)
    }
  };

  const handleBackToEmployees = () => {
    window.location.href = '/employee-list'
  };

  return (
    <div className='form-container'>
      <div className='form-content'>
      <h1>Update Employee</h1>
      
      {employeeData && (
        <form onSubmit={handleSubmit}>
          <label>
            First Name:
            <input
              type="text"
              name="first_name"
              value={formData.first_name}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Last Name:
            <input
              type="text"
              name="last_name"
              value={formData.last_name}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Email:
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Gender:
            <input
              type="text"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Salary:
            <input
              type="number"
              name="salary"
              value={formData.salary}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <button className='updateEmp-btn' type="submit">Update Employee</button>
        </form>
      )}
      <button className='back-btn' onClick={handleBackToEmployees}>Back to Employees</button>
      </div>
    </div>
  )
}

export default UpdateEmployee
