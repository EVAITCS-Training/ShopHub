import { Route, Routes } from 'react-router-dom'
import './App.css'
import LandingPage from './static/LandingPage'
import ProductIndex from './components/ProductIndex'
import NavBar from './static/NavBar'
import Register from './components/Register'
import Login from './components/Login'
import ProtectedRoute from './components/ProtectedRoute'

function App() {

  return (
    <>
      <NavBar />
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path='/products' element={
          //<ProtectedRoute>
            <ProductIndex />
          //</ProtectedRoute>
           } />
        <Route path='/register' element={<Register />} />
        <Route path='/login' element={<Login />} />
      </Routes>
    </>
  )
}

export default App
