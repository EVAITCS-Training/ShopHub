import { Route, Routes } from 'react-router-dom'
import './App.css'
import LandingPage from './static/LandingPage'
import ProductIndex from './components/ProductIndex'
import NavBar from './static/NavBar'

function App() {

  return (
    <>
      <NavBar />
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path='/products' element={<ProductIndex /> } />
      </Routes>
    </>
  )
}

export default App
