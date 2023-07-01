require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
let jwt = require('jsonwebtoken');
let bcrypt = require('bcrypt');
let User = require('./src/models/User');

// Create Express app
const app = express();

// Bodyparser
app.use(express.json());

mongoose.connect(process.env.MONGODB_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
}).then(() => console.log("MongoDB connected successfully")).catch((err) => console.log(err));

// Middleware for authentication
const authenticate = async (req, res, next) => {
    const token = req.headers.authorization;
    try {
        const payload = jwt.verify(token, process.env.SECRET);
        const user = await User.findById(payload.userId);
        if(!user) {
            return res.status(401).send({ message: 'You are not authenticated' });
        }
        req.user = user;
        next();
    } catch (error) {
        console.error(error);
        return res.status(401).send({ message: 'You are not authenticated' });
    }
};

// Signup Route
app.post('/signup', async (req, res) => {
    try {
        const { email, password, role } = req.body;
        const existingUser = await User.findOne({ email });
        if (existingUser) {
            return res.status(400).send({ message: 'User with that email already exists' });
        }
        const hashedPassword = await bcrypt.hash(password, 10);
        const user = new User({ email, password: hashedPassword, role });
        const savedUser = await user.save();
        const token = jwt.sign({ userId: savedUser._id, role: savedUser.role  }, process.env.SECRET);
        res.send({ token: token, user: savedUser });

    } catch (error) {
        console.error(error);
        return res.status(400).send({ message: 'Error in signing up. Please try again.' });
    }
});

// Login Route
app.post('/login', async (req, res) => {
    try {
        const { email, password } = req.body;
        const user = await User.findOne({ email });
        if (!user) {
            return res.status(404).send({ message: 'User with that email does not exist' });
        }
        const match = await bcrypt.compare(password, user.password);
        if (!match) {
            return res.status(401).send({ message: 'Invalid password' });
        }
        const token = jwt.sign({ userId: user._id, role: user.role }, process.env.SECRET);
        res.send({ token: token,  user });

    } catch (error) {
        console.error(error);
        return res.status(400).send({ message: 'Error in login. Please try again.' });
    }
});

// Testing authentication
app.get("/test", authenticate, (req, res) => {
    res.send("Your authentication was successful!!");
});

// Starting the server
app.listen(process.env.PORT, () => {
    console.log('App is running on port', process.env.PORT);
});

module.exports = app;
