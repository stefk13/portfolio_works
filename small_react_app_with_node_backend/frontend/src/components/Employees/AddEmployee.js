import React, { useState } from 'react'
import axios from 'axios'

const AddEmployee = () => {
  const [formData, setFormData] = useState({
    first_name: '',
    last_name: '',
    email: '',
    gender: 'Male',
    salary: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      await axios.post('http://localhost:3001/api/v1/emp/employees', formData)
      window.location.href = '/employee-list'
    } catch (error) {
      console.error('Add employee error:', error)
    }
  }

  return (
    <div>
      <h1>Add Employee</h1>
      <form onSubmit={handleSubmit}>
        <label>
          First Name:
          <input type="text" name="first_name" value={formData.first_name} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Last Name:
          <input type="text" name="last_name" value={formData.last_name} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Email:
          <input type="email" name="email" value={formData.email} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Gender:
          <label>
            <input
              type="radio"
              name="gender"
              value="Male"
              checked={formData.gender === 'Male'}
              onChange={handleChange}
              required
            />
            Male
          </label>
          <label>
            <input
              type="radio"
              name="gender"
              value="Female"
              checked={formData.gender === 'Female'}
              onChange={handleChange}
              required
            />
            Female
          </label>
          <label>
            <input
              type="radio"
              name="gender"
              value="Other"
              checked={formData.gender === 'Other'}
              onChange={handleChange}
              required
            />
            Other
          </label>
        </label>
        <br />
        <label>
          Salary:
          <input type="number" name="salary" value={formData.salary} onChange={handleChange} required />
        </label>
        <br />
        <button type="submit">Add Employee</button>
      </form>
    </div>
  )
}

export default AddEmployee
