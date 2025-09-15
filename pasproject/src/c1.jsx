import './App.css'

function C1({ pass, len, setlen, num, setnum, char, setchar,passref,copytopass }) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-6">
      <div className="bg-white p-6 rounded-2xl shadow-lg w-full max-w-lg space-y-4">
        
        {/* First line: input + copy button */}
        <div className="flex items-center gap-2">
          <input
            type="text"
            placeholder="password"
            readOnly
            className="flex-1 border border-gray-400 rounded-lg p-2 bg-gray-50 text-gray-700"
            value={pass}
            ref = {passref}
          />
          <button
            className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
            onClick={copytopass}
          >
            Copy
          </button>
        </div>

        {/* Second line: range + checkboxes */}
        <div className="flex items-center gap-4 flex-wrap">

          {/* Range input (length) */}
          <div className="flex items-center gap-2">
            <input
              type="range"
              min="8"
              max="100"
              value={len}
              className="accent-blue-500 cursor-pointer"
              onChange={(e) => setlen(Number(e.target.value))}
            />
            <label className="text-sm font-medium text-gray-700">
              Length: {len}
            </label>
          </div>

          {/* Number checkbox */}
          <div className="flex items-center gap-2">
            <input
              type="checkbox"
              id="number"
              className="w-4 h-4 accent-blue-500"
              checked={num}
              onChange={() => setnum((prev) => !prev)}
            />
            <label htmlFor="number" className="text-sm font-medium text-gray-700">
              Number
            </label>
          </div>

          {/* Special Character checkbox */}
          <div className="flex items-center gap-2">
            <input
              type="checkbox"
              id="character"
              className="w-4 h-4 accent-blue-500"
              checked={char}
              onChange={() => setchar((prev) => !prev)}
            />
            <label htmlFor="character" className="text-sm font-medium text-gray-700">
              Special Character
            </label>
          </div>
        </div>
      </div>
    </div>
  )
}

export default C1
