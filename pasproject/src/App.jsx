import { useState,useCallback, useEffect,useRef} from 'react'
import './App.css'
// import { use } from 'react'
import C1 from'./c1.jsx'

function App() {
    const [len, setlen] = useState(8)
  const [pass, setpass] = useState("")   // start with empty string
  const [num, setnum] = useState(false)
  const [char, setchar] = useState(false)
  const passref = useRef(null)

  // password generator
  const myclc = useCallback(() => {
    let p = ""
    let str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    if (num) {
      str += "0123456789"
    }
    if (char) {
      str += "@!$%^&-+?,./"
    }

    for (let i = 0; i < len; i++) {
      let charIndex = Math.floor(Math.random() * str.length)
      p += str.charAt(charIndex)
    }

    setpass(p)
  }, [num, char, len])

  useEffect(() => {
    myclc()
  }, [num, len, char, myclc])
  const copytopass = useCallback(()=>{
    passref.current?.select()
    window.navigator.clipboard.writeText(pass)

  },[pass])


  return (
     <C1
      pass={pass}
      len={len}
      setlen={setlen}
      num={num}
      setnum={setnum}
      char={char}
      setchar={setchar}
      passref = {passref}
      copytopass={copytopass}
     
     />
  )
}

export default App
