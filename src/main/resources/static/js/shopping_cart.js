$(document).ready(function () {
    $(".minusButton").on("click", function (evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });
    // var productId = "[[${product.id}]]";
    $(".plusButton").on("click", function (evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    });
    $(".link-delete").on("click", function (evt) {
        evt.preventDefault();
        deleteFromCart($(this));
    });
    updateTotal();
});

function deleteFromCart(link) {
    let url = link.attr("href");
    $.ajax({
        type: "GET",
        url: url

    }).done(function (response) {
        alert(response);
        if (response.includes("удален")) {
            $("#myModal").on("hide.bs.modal", function (e) {
                rowNumber = link.attr("rowNumber");
                updateTotal();

            });
            window.location.reload();
        }
        $("#modalBody").text(response);

    });
}

function decreaseQuantity(link) {
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);

    newQty = parseInt(qtyInput.val()) - 1;
    if (newQty > 0) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

function increaseQuantity(link) {
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);

    newQty = parseInt(qtyInput.val()) + 1;
    if (newQty < 10) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);

    }
}

function updateQuantity(productId, quantity) {
    let url = contextPath + "cart/update/" + productId + "/" + quantity;

    $.ajax({
        type: "GET",
        url: url

    }).done(function (newSubtotal) {
        updateSubtotal(newSubtotal, productId);
        updateTotal();
    });
}

function updateSubtotal(newSubtotal, productId) {
    $("#subtotal" + productId).text(newSubtotal);

}

function updateTotal() {
    total = 0.0;

    $(".productTotalItem").each(function (index, element) {
        total = total + parseFloat(element.innerHTML)
    });

    $("#totalAmount").text(total + "p");


}
