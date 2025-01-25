# Signup
This repository contains the updated Signup API, which handles the account creation process for individual users and restaurants. It integrates with the existing login API to authenticate users and confirm account creation through email verification.

## Features
- **Individual Account Creation:** Allows users to create accounts with personal details, dietary preferences, location settings, and notification preferences.
- **Restaurant Account Creation:** Enables restaurants to register with details like name, owner information, address, and cuisine type.
- **Email Verification:** Sends verification emails to confirm account creation.
- **Secure Password Storage:** Utilizes secure hashing algorithms for password storage.
- **Data Validation:** Ensures input validation to prevent common security threats.
- **Integration with Login API:** Seamlessly integrates with the existing login API for authentication.

## Endpoints

### Create Individual Account
- **Endpoint:** `/api/acc### Account Creation API Implementation Guide
**Overview:** This API manages the account creation process for individual users and restaurants. It handles data collection, validation, and storage in the database, integrating with the existing login API to authenticate users and confirm account creation through email verification.

**API Endpoints:**

1. **Create Individual Account**
   - **Endpoint:** `/api/accounts/create/individual`
   - **Method:** `POST`
   - **Body Parameters:**
     - `fullName`: string (required)
     - `email`: string (required)
     - `password`: string (required)
     - `dietaryPreferences`: array of strings (options: "Nut-Free", "Seed Oil-Free", "Gluten-Free", "Dairy-Free", "Kosher", "Halal", "Vegetarian", "Vegan", "Farm to Table")
     - `location`: string (optional)
     - `useGPS`: boolean (optional)
     - `notifications`: object (optional)
       - `emailAlerts`: boolean
       - `pushNotifications`: boolean
   - **Success Response:**
     - **Code:** 200
     - **Content:** `{ "message": "User account created successfully. Verification email sent.", "userId": "uniqueUserId" }`
   - **Error Response:**
     - **Code:** 400/500
     - **Content:** `{ "error": "Error description" }`

2. **Create Restaurant Account**
   - **Endpoint:** `/api/accounts/create/restaurant`
   - **Method:** `POST`
   - **Body Parameters:**
     - `restaurantName`: string (required)
     - `ownerName`: string (required)
     - `email`: string (required)
     - `password`: string (required)
     - `address`: string (required)
     - `phoneNumber`: string (required)
     - `cuisineType`: string (required)
     - `subscriptionPlan`: string (required, options: "Basic", "Premium")
   - **Success Response:**
     - **Code:** 200
     - **Content:** `{ "message": "Restaurant account created successfully. Verification email sent.", "restaurantId": "uniqueRestaurantId" }`
   - **Error Response:**```python
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_mail import Mail, Message
from passlib.hash import pbkdf2_sha256
import jwt
import os
from datetime import datetime, timedelta

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///filtertofork.db'
app.config['SECRET_KEY'] = 'your_secret_key'
app.config['MAIL_SERVER'] = 'smtp.yourmailserver.com'
app.config['MAIL_PORT'] = 587
app.config['MAIL_USERNAME'] = 'your-email@example.com'
app.config['MAIL_PASSWORD'] = 'your-email-password'
app.config['MAIL_USE_TLS'] = True
app.config['MAIL_USE_SSL'] = False

db = SQLAlchemy(app)
mail = Mail(app)

# Define User and Restaurant Models
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    full_name = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(100), unique=True, nullable=False)
    password_hash = db.Column(db.String(200), nullable=False)
    is_verified = db.Column(db.Boolean, default=False)

class Restaurant(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    owner_name = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(100), unique=True, nullable=False)
    password_hash = db.Column(db.String(200), nullable=False)
    is_verified = db.Column(db.Boolean, default=False)

db.create_all()
```from flask import Flask, request, jsonify
from werkzeug.security import generate_password_hash
import validators

app = Flask(__name__)

@app.route('/api/accounts/create/individual', methods=['POST'])
def create_individual():
    data = request.json
    if not validators.email(data['email']):
        return jsonify({"error": "Invalid email format"}), 400
    hashed_password = generate_password_hash(data['password'])
    # Insert into database and send verification email
    return jsonify({"message": "User account created successfully. Verification email sent.", "userId": "uniqueUserId"}), 200

@app.route('/api/accounts/create/restaurant', methods=['POST'])
def create_restaurant():
    data = request.json
    hashed_password = generate_password_hash(data['password'])
    # Insert into database and send verification email
    return jsonify({"message": "Restaurant account created successfully. Verification email sent.", "restaurantId": "uniqueRestaurantId"}), 200

if __name__ == '__main__':
    app.run(debug=True)
```

This guide and sample code snippet provide a comprehensive roadmap for the operator to implement the account creation API that links seamlessly with the existing login API.ounts/create/individual`
- **Method:** `POST`
- **Body Parameters:**
  - `fullName`: string (required)
  - `email`: string (required)
  - `password`: string (required)
  - `dietaryPreferences`: array of strings (optional)
  - `location`: string (optional)
  - `useGPS`: boolean (optional)
  - `notifications`: object (optional)
    - `emailAlerts`: boolean
    - `pushNotifications`: boolean
- **Success Response:**
  - **Code:** 200
  - **Content:** `{ "message": "User account created successfully. Verification email sent.", "userId": "uniqueUserId" }`
- **Error Response:**
  - **Code:** 400/500
  - **Content:** `{ "error": "Error description" }`

### Create Restaurant Account
* **Endpoint:** `/api/accounts/create/restaurant`
* **Method:** `POST`
* **Body Parameters:**
  * `restaurantName`: string (required)
  * `ownerName`: string (required)
  * `email`: string (required)
  * `password`: string (required)
  * `address`: string (required)
  * `phoneNumber`: string (required)
  * `cuisineType`: string (required)
* **Success Response:**
  * **Code:** 200
  * **Content:** `{ "message": "Restaurant account created successfully. Verification email sent.", "restaurantId": "uniqueRestaurantId" }`
* **Error Response:**
  * **Code:** 400/500
  * **Content:** `{ "error": "Error description" }`


## Setup
1. Clone the repository.
2. Install the required dependencies.
3. Configure the database and email settings.
4. Run the Flask application.

## Contributing
Contributions are welcome! Please submit a pull request for any improvements or fixes.

## License
This project is licensed under the MIT License.
