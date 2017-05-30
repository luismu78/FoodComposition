package es.cervecitas.food.foodcomposition.ui.principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.Utils;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGActivity;
import es.cervecitas.food.foodcomposition.ui.nutrientes.NutrientesActivity;
import es.cervecitas.food.foodcomposition.ui.search.SearchActivity;

public class PrincipalActivity extends AppCompatActivity {

    @BindView(R.id.tvNutrientes)
    TextView tvNutrientes;

    @BindView(R.id.tvGrupos)
    TextView tvGrupos;

    @BindView(R.id.tvAlimentos)
    TextView tvAlimentos;

    // Footer
    @BindView(R.id.tvFooter)
    TextView tvFooter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        tvFooter.setText(Utils.fromHtml(getString(R.string.footer_bedca)));

        manageClicks();
    }

    private void manageClicks() {

        tvNutrientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, NutrientesActivity.class);
                startActivity(intent);
            }
        });

        tvGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, FGActivity.class);
                startActivity(intent);
            }
        });

        tvAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

}
