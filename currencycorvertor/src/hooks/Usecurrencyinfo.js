import { useState, useEffect } from "react";

function UseCurrencyInfo(currency) {
  const [data, setData] = useState({});
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Reset states
    setLoading(true);
    setError(null);

    // Validate currency
    if (!currency || typeof currency !== "string" || currency.trim() === "") {
      setError("Invalid currency code");
      setLoading(false);
      return;
    }

    // Fetch exchange rates
    fetch(`https://open.er-api.com/v6/latest/${currency}`)
      .then((res) => {
        console.log(res)
        if (!res.ok) {
          throw new Error(`HTTP error! Status: ${res.status}`);
        }
        return res.json();
      })
      .then((res) => {
        if (res.result === "error") {
          throw new Error(res["error-type"] || "Invalid API response");
        }
        setData(res.rates || {});
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message || "Failed to fetch currency data");
        setLoading(false);
      });
  }, [currency]);

  return { data, error, loading };
}

export default UseCurrencyInfo;