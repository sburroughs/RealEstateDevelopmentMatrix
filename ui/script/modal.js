/**
 * @file
 * A JavaScript file for the Modal.
 *
 * It is loaded by the layout plugin.
 */

// JavaScript should be made compatible with libraries other than jQuery by
// wrapping it with an "anonymous closure".
// Function for hiding and displaying the modal
$(document).on('click', '.toggle--modal', function (e) {
    e.preventDefault();
    e.stopPropagation();
    $('.modal__overlay').toggleClass('is-active');
    $('body').toggleClass('is-fixed');
});