# Lens production lessons learnt

* Integration test oracles became readable
  - Several bugs discovered
* Robustness to field changes
  - Many tests care about few fields
  - Schema changes
  - Semantic changes
* Easier to write integration test oracles
  - Test only relevant fields
* Replacing unit tests with integration
  - Better documentation of pipeline behaviour
  - Some unit tests turned out to test unreachable code

