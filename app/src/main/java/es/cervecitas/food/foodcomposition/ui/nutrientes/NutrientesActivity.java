package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import es.cervecitas.food.foodcomposition.R;

public class NutrientesActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutriente);
    }

    /**
     * <?xml version="1.0" encoding="utf-8"?>
     <foodquery>
     <type level="1a"/>
     <selection>
     <atribute name="f_id"/>
     <atribute name="f_ori_name"/>
     <atribute name="langual"/>
     <atribute name="f_eng_name"/>
     <atribute name="c_ori_name"/>
     <atribute name="best_location"/>
     <atribute name="v_unit"/>
     <atribute name="moex"/>
     <atribute name="f_origen"/>
     </selection>
     <condition>
     <cond1><atribute1 name="c_id"/></cond1>
     <relation type="EQUAL"/>
     <cond3>409</cond3>
     </condition>
     <condition>
     <cond1><atribute1 name="f_origen"/></cond1>
     <relation type="EQUAL"/>
     <cond3>BEDCA</cond3>
     </condition>
     <order ordtype="DESC"><atribute3 name="best_location"/></order>
     </foodquery>
     */
}
