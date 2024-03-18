import axios from 'axios'
import React, { useState } from 'react'
import '../CSS/signup.css';

const Signup = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    })
  }

  const handleSignup = async () => {
    try {
      const response = await axios.post(
        'http://localhost:3001/api/v1/user/signup',
        formData
      )
      console.log(response.data)
      window.location.href = '/login'
    } catch (error) {
      console.error('Signup error:', error)
    }
  };

  return (
    <div className="signup-container">
      <div className='signup-content'>
        <h1>Signup</h1>
          <form>
            <label className='label'>
              Username:
              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
            </label>
            <br />
            <label className='label'>
              Email:
              <input
                type="text"
                name="email"
                value={formData.email}
                onChange={handleChange}
              />
            </label>
            <br />
            <label className='label'>
              Password:
              <input
                className='input'
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
            </label>
            <br />
            <button type="button" onClick={handleSignup}>
              Signup
            </button>
          </form>
        </div>
    </div>
  )
}

export default Signup
