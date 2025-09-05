import { Navigate } from "react-router-dom"


export default function ProtectedRoute({children}) {
    const token = sessionStorage.getItem("jwt")

    const isValidToken = () => {
        if (!token) return false

        try {
            const payload = JSON.parse(atob(token.split('.')[1]))
            const currentTime = Date.now() / 1000

            return payload.exp > currentTime
        } catch (error) {
            return false
        }
    }
  return isValidToken() ? children : <Navigate to="/login" replace />
}
