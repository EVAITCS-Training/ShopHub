import * as yup from 'yup';
import { useFormik } from 'formik';

export default function AddProduct() {
    const validationSchema =  yup.object({
        name: yup.string().min(5, "Product name must be at least 5 characters").required("Product name is required"),
        price: yup.number().min(0.49, "Price must be at least 0.49").required("Price is required"),
        quantity: yup.number().min(25, "Quantity must be at least 25").required("Quantity is required"),
        description: yup.string().min(10, "Description must be 10 characters long").max(300, "Description can't be longer then 300 characters").required("Description is required")
    })

    const formik = useFormik({
        initialValues: {
            name: '',
            price: 0,
            quantity: 0,
            description: ''
        },
        validationSchema: validationSchema,
        onSubmit: () => {}
    })
  return (
    <div>AddProduct</div>
  )
}
