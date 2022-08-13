function addRecipe() {
  var a = document.createElement("a");
  var li = document.createElement("li");
  var l = document.getElementById("url").value;
  var name = document.getElementById("name").value;
  var description = document.getElementById("d").value;
  var c = document.getElementById("category");
  var category = c.options[c.selectedIndex].text;
  a.appendChild(document.createTextNode(name));
  a.href = l;
  li.appendChild(a);
  li.appendChild(document.createTextNode(" " + description));
  document.getElementById(category).appendChild(li);
  var rem = document.createElement("span");
  rem.className = "remove";
  rem.appendChild(document.createTextNode("X"));
  rem.onclick = function () {
    this.parentNode.remove();
  };
  li.appendChild(rem);
  document.getElementById("url").value = "";
  document.getElementById("name").value = "";
  document.getElementById("d").value = "";
}
