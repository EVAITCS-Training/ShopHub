import * as yup from 'yup';
import { useFormik } from 'formik';
import { Dialog, DialogTitle, DialogContent, TextField, DialogActions, Button, FilledInput, InputAdornment, InputLabel, Input, FormHelperText } from '@mui/material';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import type { CartItem } from '../context/CartContext';

export default function AddProduct({open, handleClose}: {open:boolean, handleClose: () => void}) {
    const queryClient = useQueryClient();
    const validationSchema =  yup.object({
        name: yup.string().min(5, "Product name must be at least 5 characters").required("Product name is required"),
        price: yup.number().min(0.49, "Price must be at least 0.49").required("Price is required"),
        quantity: yup.number().min(25, "Quantity must be at least 25").required("Quantity is required"),
        description: yup.string().min(10, "Description must be 10 characters long").max(300, "Description can't be longer then 300 characters").required("Description is required")
    })
    const {mutate, isPending} = useMutation({
      mutationFn: async (data:CartItem) => {
        const res = axios.post(import.meta.env.VITE_API_URL + "product/add", data, {
          headers: {
            "Content-Type": "application/json"
          }
        })
        return res
      },
      onSuccess:() => {
        queryClient.invalidateQueries()
        closeModal()
      },
      onError:(error) => {
        alert(error.message)
      }
    })
    const formik = useFormik({
        initialValues: {
            name: '',
            price: 0,
            quantity: 0,
            description: ''
        },
        validationSchema: validationSchema,
        onSubmit: (values) => mutate(values)
    })

    const closeModal = () => {
      formik.resetForm()
      handleClose()
    }
  return (
    <Dialog open={open} onClose={closeModal}>
        <DialogTitle>Add new product</DialogTitle>
        <DialogContent>
          <form onSubmit={formik.handleSubmit} id="add-product-form">
            <TextField
              autoFocus
              required
              margin="dense"
              id="name"
              name="name"
              label="Name"
              type="text"
              value={formik.values.name}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              error={formik.touched.name && Boolean(formik.errors.name)}
              helperText={formik.touched.name && formik.errors.name}
              fullWidth
              variant="standard"
            />
            <TextField
              required
              margin='dense'
              id='description'
              name='description'
              label='Description'
              type='textarea'
              value={formik.values.description}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              error={formik.touched.description && Boolean(formik.errors.description)}
              helperText={formik.touched.description && formik.errors.description}
              fullWidth
              variant='standard'
              />
              <InputLabel htmlFor="price">Price</InputLabel>
              <FilledInput
                id="price"
                startAdornment={<InputAdornment position="start">$</InputAdornment>}
                type='number'
                inputProps={{
                  step: 0.05,
                  min: 0.49
                }}
                required
                fullWidth
                value={formik.values.price}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.price && Boolean(formik.errors.price)}
                //helperText={formik.touched.name && formik.errors.name}
              />
              { formik.touched.price && formik.errors.price && 
                (<FormHelperText>
                  {formik.errors.price}
              </FormHelperText>)}
              <Input
                type='number'
                id='quantity'
                name='quantity'
                placeholder='Quantity'
                required
                fullWidth
                value={formik.values.quantity}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.quantity && Boolean(formik.errors.quantity)}
                //helperText={formik.touched.quantity && formik.errors.quantity}
                />
                 { formik.touched.quantity && formik.errors.quantity && 
                (<FormHelperText>
                  {formik.errors.quantity}
              </FormHelperText>)}
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={closeModal}>Cancel</Button>
          <Button type="submit" form="add-product-form" disabled={isPending}>
            Add Product
          </Button>
        </DialogActions>
      </Dialog>
  )
}
