import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
//import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Layout from './Layout.jsx'
import { Home } from './component/Index.jsx'
import About from './component/About/About.jsx'
import Contact from './component/Contact/Contact.jsx'
import User from './component/User/User.jsx'
const router = createBrowserRouter(
  [
    {
    path:"/",
    element: <Layout/>,
    children:[
        {
          path:"",
          element:<Home/>
        },
        {
          path:"About",
          element:<About/>
        },
        {
          path:"Contact",
          element:<Contact/>
        },
        {
          path:'User/:userid',
          element:<User/>
        }


    ]

    }


  ]
)
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>,
)
