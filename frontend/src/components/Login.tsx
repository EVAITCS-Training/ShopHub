import { Box, Container, Input, TextField } from '@mui/material'
import axios from 'axios'
import { useFormik } from 'formik'
import { useNavigate } from 'react-router-dom'
import * as yup from 'yup'

type AuthRequest = {
    email: string,
    password: string
}

export default function Login() {
  const navigate = useNavigate();
    const validationSchema = yup.object({
        email: yup.string().required("Email is required for login"),
        password: yup.string().required("Password is required")
    })
    const formik = useFormik({
        initialValues: {
            email: '',
            password: ''
        },
        validationSchema: validationSchema,
        onSubmit: async (values: AuthRequest) => {
            await axios.post(import.meta.env.VITE_API_URL + "auth/login",
                values,
                {
                    headers: {
                        "Content-Type" : "application/json"
                    }
                }
            ).then(response => {
                sessionStorage.setItem("jwt", response.data)
                alert("Login Successful")
                navigate("/products")
            })
        }
    })
  return (
    <Container maxWidth="md">
        <Box sx={{ mt: 4 }} component="form" onSubmit={formik.handleSubmit}>
            <TextField 
            fullWidth 
            required
            id='email'
            label="Email Address"
            type='email'
            value={formik.values.email}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            error={formik.touched.email && Boolean(formik.errors.email)}
            helperText={formik.touched.email && formik.errors.email || "Please enter your current email"}
            />
            <TextField 
            fullWidth 
            required
            id='password'
            label="Password"
            type='password'
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            error={formik.touched.password && Boolean(formik.errors.password)}
            helperText={formik.touched.password && formik.errors.password || "Please enter a strong password"}
            />
            <Input
             type='submit'
             value="Register User"
             disabled={!(formik.isValid && formik.dirty) || formik.isSubmitting}
             />
        </Box>
    </Container>
  )
}
