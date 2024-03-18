const express = require("express")
const UserModel = require('../models/User')
const routes = express.Router()


routes.post("/signup", async (req, res) => {
    try {

        const newUser = new UserModel({
            ...req.body
        })

        await newUser.save();
        res.json({
            status: true,
            username : newUser.username,
            message: "User logged in successfully"
          });

    } catch (error) {
        res.status(500).send(error)
    }
});


routes.post("/login", async (req, res) => {
  try {
      const { username, password } = req.body;

      const user = await UserModel.findOne({ username })

      if (!user) {
          res.status(401).json({
              status: false,
              message: "User not found"
          })
      } else if (user.password === password) {
          res.status(200).json({
              status: true,
              username: user.username,
              message: "Login is successful"
          })
      } else {
          res.status(401).json({
              status: false,
              message: "Password is invalid"
          })
      }
  } catch (error) {
      res.status(500).send(error)
  }
})

module.exports = routes;


