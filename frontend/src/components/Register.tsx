import { Box, Container, Input, TextField } from '@mui/material'
import axios from 'axios'
import { useFormik } from 'formik'
import { useNavigate } from 'react-router-dom'
import * as yup from 'yup'

type AuthRequest = {
    email: string,
    password: string
}

export default function Register() {
    const navigate = useNavigate();
    const validationSchema = yup.object({
        email: yup.string().email("Has to be a valid email setup").required("Email is required for registration"),
        password: yup.string().matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/, "Password must contain 1 Upper, 1 Lower, 1 Number, and 1 Special Character").required("Password is required")
    })
    const formik = useFormik({
        initialValues: {
            email: '',
            password: ''
        },
        validationSchema: validationSchema,
        onSubmit: async (values: AuthRequest) => {
            const res = await axios.post(import.meta.env.VITE_API_URL + "auth/register",
                values,
                {
                    headers: {
                        "Content-Type" : "application/json"
                    }
                }
            ).then(response => {
                alert(response.data)
                navigate("/login")
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
