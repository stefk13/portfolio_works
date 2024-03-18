const mongoose = require('mongoose')

const userSchema = mongoose.Schema({
username: {
    type: String,
    required: true,
    unique: true,
    maxlength: 100,
  },
  email: {
    type: String,
    required: true,
    unique: true,
    maxlength: 50,
  },
  password: {
    type: String,
    maxlength: 50,
  },
})

module.exports = mongoose.model('User', userSchema)

