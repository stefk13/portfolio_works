import axios from 'axios'
import { useState } from 'react'
import '../CSS/login.css';

const Login = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  })

  const userCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('user='))?.split('=')[1]
  if (userCookie) {
    return window.location.href = '/employee-list'
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }))
  }

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:3001/api/v1/user/login', formData)
      console.log(response.data)
      const userData = response.data
      document.cookie = `user=${userData.username}; expires=Wed, 1 Jan 2070 13:47:11 UTC; path=/; SameSite=None; Secure`
      window.location.href = '/employee-list'
    } catch (error) {
      console.error('Login error:', error)
    }
  }

  return (
    <div className='login-container'>
      <div className='login-content'>
          <h1>Login</h1>
          <form>
            <label>
              Username:
              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
              />
            </label>
            <br />
            <label>
              Password:
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
              />
            </label>
            <br />
            <button type="button" onClick={handleLogin}>
              Login
            </button>
          </form>
        </div>
    </div>
  )
}

export default Login
