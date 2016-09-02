# Lens production experience

* Spotify data pipeline for royalty calculations, label reporting, user behavioural analysis.
  - Each track played, decorated with demography, product data, licensing.
  - User activity, e.g. device types, free/premium, shuffling.
* Complexity due to complex business rules, i.e. label/partner contracts.
* Apache Crunch + Apache Scalding, both on top of Hadoop MapReduce.
  - Integration tests with 1-2 jobs in isolation. Avro in & out.
  - Unit tests.
