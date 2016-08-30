# scalaz.ValidationNel

* Asymmetric dual type - error or T
* Eagerly evaluates all operations
  - Combined with |@| operator (aka oink, tie fighter, bad piggy)
  - Saves all errors in non-empty list (Nel)
* Useful when reporting all errors is desired, e.g. user filling in form.

Issues:
* Even more Ascii art
  - Further down the incomprehensible slope
  - Scalaz examples: `<<?:   +|+   \?/   <+>   <++>   <%=   :++>>   ?|?`
* Steeper learning curve
  - I lost patience on simple case
