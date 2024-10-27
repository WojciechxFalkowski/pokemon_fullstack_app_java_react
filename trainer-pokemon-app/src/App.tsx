import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { fetchTrainers } from "./store/trainersSlice";
import TrainerCards from "./components/TrainerCards";
import { AppDispatch } from "./store/store";

const App: React.FC = () => {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    dispatch(fetchTrainers());
  }, [dispatch]);

  return (
    <div className="App">
      <TrainerCards />
    </div>
  );
};

export default App;
