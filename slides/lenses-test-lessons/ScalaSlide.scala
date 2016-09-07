package com.mapflat.presentations.funcpatterns

class ScalaSlide {
  """
    * Converting to oracle tests based on lenses
      - Tests robust to unrelated changes
      - Less work crafting expected output
      - Oracle duplication factored out

    * Easier to write integration tests to cover specific corners
      - Data processing very suited to integration tests
      - Less suited to unit tests - internals change frequently

    * Enabled us to write oracle invariants
      - E.g. assert(data.activity.total >= data.activity.android)
      - Reusable for data quality production monitoring

    (Test oracle == thing that verifies that tests pass)
  """
}
