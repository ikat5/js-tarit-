import React from 'react'
import { useParams } from 'react-router-dom'

function User() {
    const { userid } = useParams() 
  return (
    <div className="flex items-center justify-center h-40 bg-gray-200 rounded-lg shadow-md">
      <span className="text-xl font-semibold text-gray-800">
        User: {userid}
      </span>
    </div>
  )
}

export default User