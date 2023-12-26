# capacitor-facebook-events

Facebook events tracking in Capacitor applications

## Install

```bash
npm i --save capacitor-facebook-events
npx cap sync
```

## API

<docgen-index>

* [`setAdvertiserTrackingEnabled(...)`](#setadvertisertrackingenabled)
* [`logEvent(...)`](#logevent)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### setAdvertiserTrackingEnabled(...)

```typescript
setAdvertiserTrackingEnabled(options: { enabled: boolean; }) => Promise<void>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ enabled: boolean; }</code> |

--------------------


### logEvent(...)

```typescript
logEvent(options: { event: string; params?: any; }) => Promise<void>
```

| Param         | Type                                          |
| ------------- | --------------------------------------------- |
| **`options`** | <code>{ event: string; params?: any; }</code> |

--------------------

</docgen-api>

## Example Usage

### Logging a Registration Event
To log a registration event, use the 'fb_mobile_complete_registration' event name:

```typescript
import { FacebookEvents } from 'capacitor-facebook-events';

// ...

FacebookEvents.logEvent({
event: 'fb_mobile_complete_registration',
params: {
// Additional parameters (optional)
}
});
```

### Logging a Purchase Event
For logging a purchase event, use the 'fb_mobile_purchase' event name with relevant parameters:

```typescript
import { FacebookEvents } from 'capacitor-facebook-events';

// ...

FacebookEvents.logEvent({
    event: 'fb_mobile_purchase',
    params: {
        fb_content_id: 'item_id', // Item ID
        fb_content_type: 'product',
        fb_currency: 'currency_code',
        _valueToSum: amount // Purchase amount
    }
});
```

For a comprehensive list of events, refer to the [Facebook App Events API documentation](https://developers.facebook.com/docs/marketing-api/app-event-api/).
