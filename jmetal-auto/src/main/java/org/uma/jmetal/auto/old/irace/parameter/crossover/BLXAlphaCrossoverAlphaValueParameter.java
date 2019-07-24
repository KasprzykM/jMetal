package org.uma.jmetal.auto.old.irace.parameter.crossover;

import org.uma.jmetal.auto.old.irace.parametertype.impl.RealParameterType;

public class BLXAlphaCrossoverAlphaValueParameter extends RealParameterType {
  public BLXAlphaCrossoverAlphaValueParameter() {
    super("blxAlphaCrossoverAlphaValue", 0.0, 1.0) ;

    setParentTag(CrossoverType.BLX_ALPHA.name());
  }
}