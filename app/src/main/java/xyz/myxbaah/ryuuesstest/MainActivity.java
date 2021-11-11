package xyz.myxbaah.ryuuesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.LessClause;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;

public class MainActivity extends AppCompatActivity {

    private TextView txtOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOutput = findViewById(R.id.txtOutput);

        testForwardChain();
    }

    private RuleInferenceEngine getInferenceEngine() {
        RuleInferenceEngine rie = new KieRuleInferenceEngine();

        Rule rule = new Rule("Bicycle");
        rule.addAntecedent(new EqualsClause("vehicleType", "cycle"));
        rule.addAntecedent(new EqualsClause("num_wheels", "2"));
        rule.addAntecedent(new EqualsClause("motor", "no"));
        rule.setConsequent(new EqualsClause("vehicle", "Bicycle"));
        rie.addRule(rule);

        rule = new Rule("Tricycle");
        rule.addAntecedent(new EqualsClause("vehicleType", "cycle"));
        rule.addAntecedent(new EqualsClause("num_wheels", "3"));
        rule.addAntecedent(new EqualsClause("motor", "no"));
        rule.setConsequent(new EqualsClause("vehicle", "Tricycle"));
        rie.addRule(rule);

        rule = new Rule("Motorcycle");
        rule.addAntecedent(new EqualsClause("vehicleType", "cycle"));
        rule.addAntecedent(new EqualsClause("num_wheels", "2"));
        rule.addAntecedent(new EqualsClause("motor", "yes"));
        rule.setConsequent(new EqualsClause("vehicle", "Motorcycle"));
        rie.addRule(rule);

        rule = new Rule("SportsCar");
        rule.addAntecedent(new EqualsClause("vehicleType", "automobile"));
        rule.addAntecedent(new EqualsClause("size", "medium"));
        rule.addAntecedent(new EqualsClause("num_doors", "2"));
        rule.setConsequent(new EqualsClause("vehicle", "Sports_Car"));
        rie.addRule(rule);

        rule = new Rule("Sedan");
        rule.addAntecedent(new EqualsClause("vehicleType", "automobile"));
        rule.addAntecedent(new EqualsClause("size", "medium"));
        rule.addAntecedent(new EqualsClause("num_doors", "4"));
        rule.setConsequent(new EqualsClause("vehicle", "Sedan"));
        rie.addRule(rule);

        rule = new Rule("MiniVan");
        rule.addAntecedent(new EqualsClause("vehicleType", "automobile"));
        rule.addAntecedent(new EqualsClause("size", "medium"));
        rule.addAntecedent(new EqualsClause("num_doors", "3"));
        rule.setConsequent(new EqualsClause("vehicle", "MiniVan"));
        rie.addRule(rule);

        rule = new Rule("SUV");
        rule.addAntecedent(new EqualsClause("vehicleType", "automobile"));
        rule.addAntecedent(new EqualsClause("size", "large"));
        rule.addAntecedent(new EqualsClause("num_doors", "4"));
        rule.setConsequent(new EqualsClause("vehicle", "SUV"));
        rie.addRule(rule);

        rule = new Rule("Cycle");
        rule.addAntecedent(new LessClause("num_wheels", "4"));
        rule.setConsequent(new EqualsClause("vehicleType", "cycle"));
        rie.addRule(rule);

        rule = new Rule("Automobile");
        rule.addAntecedent(new EqualsClause("num_wheels", "4"));
        rule.addAntecedent(new EqualsClause("motor", "yes"));
        rule.setConsequent(new EqualsClause("vehicleType", "automobile"));
        rie.addRule(rule);

        return rie;
    }

    public void testForwardChain() {

        RuleInferenceEngine rie = getInferenceEngine();
        rie.addFact(new EqualsClause("num_wheels", "4"));
        rie.addFact(new EqualsClause("motor", "yes"));
        rie.addFact(new EqualsClause("num_doors", "3"));
        rie.addFact(new EqualsClause("size", "medium"));

        txtOutput.setText("Before inference:\n");
        String text = txtOutput.getText().toString();
        text = text + rie.getFacts().toString() + "\n";
        txtOutput.setText(text);

        rie.infer(); //forward chain

        text = text + "After inference:\n";
        txtOutput.setText(text);
        text = text + rie.getFacts().toString() + "\n";
        txtOutput.setText(text);
    }
}