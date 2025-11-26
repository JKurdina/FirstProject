package com.example.files;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {
    private final List<Planet> planets;

    public PlanetAdapter(List<Planet> planets) {
        this.planets = planets;
    }
    @NonNull
    @Override
    public PlanetAdapter.PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_planet, parent, false);
        return new PlanetAdapter.PlanetViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull PlanetAdapter.PlanetViewHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.name.setText(planet.getName());
        holder.diameter_km.setText(
                String.format("%.0f км", planet.getDiameter())
        );
        holder.distance_from_sun_mkm.setText(
                String.format("%,.0f млн км", planet.getDistanceFromSun())
        );
        holder.moons.setText(String.format("%,d", planet.getMoons()));
        holder.fact.setText(planet.getFact());
        holder.day_length_hours.setText(String.format("%.0f ч", planet.getDayLength()));
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    static class PlanetViewHolder extends RecyclerView.ViewHolder {
        TextView name, diameter_km, distance_from_sun_mkm, moons, day_length_hours, fact;

        public PlanetViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvPlanetName);
            diameter_km = itemView.findViewById(R.id.tvDiameter);
            distance_from_sun_mkm = itemView.findViewById(R.id.tvDistance);
            moons = itemView.findViewById(R.id.tvMoons);
            fact = itemView.findViewById(R.id.tvFact);
            day_length_hours = itemView.findViewById(R.id.tvDayLength);

        }
    }
}
