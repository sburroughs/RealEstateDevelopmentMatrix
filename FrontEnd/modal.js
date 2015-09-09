/**
 * @file
 * A JavaScript file for the Modal.
 *
 * It is loaded by the layout plugin.
 */

// JavaScript should be made compatible with libraries other than jQuery by
// wrapping it with an "anonymous closure".
(function ($, window, document, undefined) {

  // Function for hiding and displaying the modal
  $('.toggle--modal').once().click(function(e) {
    e.preventDefault();
    e.stopPropagation();
    $('.modal__overlay').toggleClass('is-active');
    $(this).toggleClass('is-active');
  });

})(jQuery, this, this.document);