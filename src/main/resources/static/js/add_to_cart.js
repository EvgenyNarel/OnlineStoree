$(document).ready(function(){
    $("#buttonAdd2Cart").on("click",function(e){
        addToCart();
    });
});

function addToCart(){
    quantity = $("#quantity" + productId).val();
    url = contextPath + "cart/add/" + productId + "/" + quantity;

    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",

        error: function (response) {
            alert("Добавленно")
        }
    });
}