import { useState,useEffect,useCallback } from 'react'
import { InputBox } from './component'
import useCurrencyInfo from './hooks/Usecurrencyinfo'
import './App.css'

function App() {
  const [amount, setAmount] = useState();
  const [from, setFrom] = useState('usd');
  const [to, setTo] = useState('bdt');
  const [convertedAmount, setConvertedAmount] = useState(0);

  const { data: currencyDetails, error, loading } = useCurrencyInfo(from);
  const options = Object.keys(currencyDetails || {});

  const swap = () => {
    setFrom(to);
    setTo(from);
    setConvertedAmount(amount);
    setAmount(convertedAmount);
  };

  const convert = () => {
    if (currencyDetails && currencyDetails[to]) {
      setConvertedAmount(Number((amount * currencyDetails[to]).toFixed(2)));
    }
  };
  const handelcurrencychange = useCallback((currency)=>{
    setFrom(currency)
  },[from])
  const currencychange = useCallback((currency)=>{
    setTo(currency)
  },[to])

  // useEffect(() => {
  //   if (amount && currencyDetails && currencyDetails[to]) {
  //     convert();
  //   }
  // }, [amount, from, to, currencyDetails]);

  return (
    <div
      className="w-full h-screen flex bg-center justify-center  items-center bg-cover bg-no-repeat"
      style={{
        backgroundImage: `url('https://images.pexels.com/photos/3532540/pexels-photo-3532540.jpeg')`,
      }}
    >
      <div className="w-full">
        <div className="w-full max-w-md mx-auto border border-gray-60 rounded-lg p-5 backdrop-blur-sm bg-white/30">
          {loading && <p className="text-white text-center">Loading...</p>}
          {error && <p className="text-red-500 text-center">Error: {error}</p>}
          {!loading && !error && (
            <form
              onSubmit={(e) => {
                e.preventDefault();
                convert();
              }}
            >
              <div className="w-full mb-1">
                <InputBox
                  label="From"
                  amount={amount}
                  currencyOptions={options}
                  onAmountChange={(amount) => setAmount(amount)}
                  onCurrencyChange = {
                    handelcurrencychange
                  }
                  selectCurrency={from}
                />
              </div>
              <div className="relative w-full h-0.5">
                <button
                  type="button"
                  className="absolute left-1/2 -translate-x-1/2 -translate-y-1/2 border-2 border-white rounded-md bg-blue-600 text-white px-2 py-0.5"
                  onClick={swap}
                >
                  Swap
                </button>
              </div>
              <div className="w-full mt-1 mb-4">
                <InputBox
                  label="To"
                  amount={convertedAmount}
                  currencyOptions={options}
                  onCurrencyChange={currencychange}
                  selectCurrency={to}
                 // amountDisable
                />
              </div>
              <button
                type="submit"
                className="w-full bg-blue-600 text-white px-4 py-3 rounded-lg"
              >
                Convert {from.toUpperCase()} to {to.toUpperCase()}
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;